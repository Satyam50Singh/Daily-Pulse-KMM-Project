import SwiftUI
import shared

struct ContentView: View {
	let greet = "Hello World!"
    
    let platform = Platform()
    
	var body: some View {
		Text(greet)
            .onAppear{
                platform.logSystemInfo()
            }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
