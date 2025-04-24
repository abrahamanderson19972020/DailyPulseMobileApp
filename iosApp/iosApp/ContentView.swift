import SwiftUI
import shared

struct ContentView: View {
    @State private var shouldOpen = false
	var body: some View {
	    NavigationStack{
	        ArticlesScreen(viewMode: .init())
	        .toolbar{
	            Button{
	               shouldOpen = true
	            } label("About", systemImage: "info.circle").labelStyle(.titleAndIcon)
	            .popover(isPresented:$shouldOpen){
	              //
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