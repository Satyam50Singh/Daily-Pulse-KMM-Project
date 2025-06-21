import SwiftUI
import shared

struct ContentView: View {
    
    let platform = Platform()
    
    @State private var shouldOpenAbout = false
    
    @State private var shouldOpenSources = false
    
    var body: some View {
        let articlesScreen = ArticleScreen(viewModel: .init())
        
        NavigationStack {
            ArticleScreen(viewModel: .init())
                .toolbar {
                    Button {
                        shouldOpenSources = true
                    } label: {
                        Label("Sources", systemImage: "list.bullet").labelStyle(.titleAndIcon)
                    }
                    Button {
                        shouldOpenAbout = true
                    } label: {
                        Label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
                    }
                    .popover(isPresented: $shouldOpenAbout) {
                        AboutScreen().onAppear{
                            platform.logSystemInfo()    
                        }
                    }.popover(isPresented: $shouldOpenSources){
                        SourcesScreen()
                    }
                }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
