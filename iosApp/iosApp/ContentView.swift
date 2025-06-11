import SwiftUI
import shared

struct ContentView: View {
    
    let platform = Platform()
    
	var body: some View {
        AboutScreen().onAppear{
            platform.logSystemInfo()
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
