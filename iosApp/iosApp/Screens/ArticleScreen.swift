//
//  ArticleScreen.swift
//  iosApp
//
//  Created by Satyam Singh on 13/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

extension ArticleScreen {
    @MainActor
    class ArticleViewModelWrapper :ObservableObject {
        
        let articlesViewModel: ArticlesViewModel
        
        
        init() {
            articlesViewModel = BaseInjector().articlesViewModel
            articleState = articlesViewModel.articleState.value
        }
        
        @Published var articleState: ArticleState
        
        func startObserving() {
            Task {
                for await articleS in articlesViewModel.articleState {
                    self.articleState = articleS
                }
            }
        }
    }
}


struct ArticleScreen: View {
    
    @ObservedObject private(set) var viewModel: ArticleViewModelWrapper
    
    var body: some View {
        VStack{
            AppBar(title: "Articles")
            
            if viewModel.articleState.loading {
                Loader()
            }
            
            if let error = viewModel.articleState.error {
                ErrorMessage(message:error)
            }
            
            if !(viewModel.articleState.article ?? []) .isEmpty {
                ScrollView {
                    LazyVStack(spacing:10){
                        ForEach(viewModel.articleState.article ?? [], id: \.self) { article in
                            ArticleItemView(article: article)
                        }
                    }
                }
            }
            
        }.onAppear{
            self.viewModel.startObserving()
        }
        
    }
}

struct AppBar: View {
    var title: String
    var body: some View {
        Text(title)
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct Loader:View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage:View {
    var message:String
    var body: some View {
        Text(message)
            .font(.title)
    }
}

struct ArticleItemView: View {
    
    var article: Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("Image Load Error")
                } else {
                    ProgressView()
                }
                
            }
            Text(article.title)
                .font(.headline)
                .fontWeight(.bold)
            
            Text(article.description_)
            Text(article.date).frame(maxWidth: .infinity, alignment: .trailing).foregroundStyle(.gray).font(.caption)
        }
        .padding(16)
        .background(Color.white)
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(Color.gray, lineWidth: 1)
        )
        .cornerRadius(12)
        .shadow(color: .gray.opacity(0.2), radius: 4, x: 0, y: 2)
        .padding(.horizontal)
    }
}
