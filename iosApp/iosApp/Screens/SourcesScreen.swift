//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Satyam Singh on 18/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

@MainActor
class SourcesViewModelWrapper: ObservableObject {
    
    let sourcesViewModel: SourcesViewModel
    
    init() {
        do {
            let vm = BaseInjector().sourcesViewModel
            self.sourcesViewModel = vm
            self.sourcesState = vm.sourcesState.value
        } catch {
            fatalError("Failed to initialize SourcesViewModelWrapper: \(error)")
        }
    }
    
    @Published var sourcesState: SourceState
    
    func startObserving() {
        Task {
            for await sourceS in sourcesViewModel.sourcesState {
                self.sourcesState = sourceS
            }
        }
    }
}

struct SourcesScreen: View {
    
    @Environment(\.dismiss) private var dismiss
    
    @StateObject private var viewModel = SourcesViewModelWrapper()
    
    var body: some View {
        NavigationStack {
            SourcesContentView(viewModel: viewModel)
                .toolbar {
                    ToolbarItem(placement: .principal) {
                        Text("Sources").font(.headline)
                    }
                    ToolbarItem(placement: .primaryAction) {
                        Button("Done") {
                            dismiss()
                        }
                        .bold()
                    }
                }
        }
    }
}

struct SourcesContentView: View {
    
    @ObservedObject private(set) var viewModel: SourcesViewModelWrapper
    
    var body: some View {
        ScrollView {
            LazyVStack(spacing: 16) {
                if viewModel.sourcesState.loading {
                    Loader()
                }
                
                if let error = viewModel.sourcesState.error {
                    ErrorMessage(message: error)
                }
                
                ForEach(viewModel.sourcesState.sources ?? [], id: \.self) { source in
                    SourceItemView(source: source)
                }
            }
            .padding(.horizontal, 12)
            .padding(.top, 4)
            .padding(.bottom, 16)
        }
        .padding(.top, 0)
        .scrollContentBackground(.hidden)
        .onAppear {
            viewModel.startObserving()
        }
    }
}

struct SourceItemView: View {
    
    var source: Source
    
    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            Text(source.name)
                .font(.headline)
                .fontWeight(.bold)
            
            Text(source.desc)
                .font(.subheadline)
            
            HStack {
                Spacer()
                Text(source.lang + "-" + source.country)
                    .font(.subheadline)
            }
        }
        .padding(16)
        .background(Color.white)
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(Color.gray.opacity(0.3), lineWidth: 1)
        )
        .cornerRadius(12)
        .shadow(color: .gray.opacity(0.15), radius: 4, x: 0, y: 2)
    }
}
