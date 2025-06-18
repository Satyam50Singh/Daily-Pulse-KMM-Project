import SwiftUI
import shared

struct ContentView: View {
    
    let platform = Platform()
    
    @State private var shouldOpenAbout = false
    
	var body: some View {
        let articlesScreen = ArticleScreen(viewModel: .init())
        
        NavigationStack {
            ArticleScreen(viewModel: .init())
                .toolbar {
                    Button {
                        shouldOpenAbout = true
                    } label: {
                        Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                    }
                    .popover(isPresented: $shouldOpenAbout) {
                        AboutScreen().onAppear{
                            platform.logSystemInfo()
                        }
                    }
                }

        }
        .refreshable {
            articlesScreen.viewModel.articlesViewModel.getArticle(forceRefresh: true)
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
