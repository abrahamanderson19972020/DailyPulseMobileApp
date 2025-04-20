import SwiftUI
import shared

import Combine

extension ArticlesScreen {
   @MainActor
   class ArticlesViewModelWrapper: ObservableObject {
      private var cancellables = Set<AnyCancellable>()
      let articlesViewModel: ArticlesViewModel

      init() {
         articlesViewModel = ArticlesViewModel()
         articlesState = articlesViewModel.articlesState.value
      }

      @Published var articlesState: ArticlesState

      func startObserving() {
         // Use Combine to observe changes in the articlesState
         articlesViewModel.$articlesState
            .sink { [weak self] newState in
                self?.articlesState = newState
            }
            .store(in: &cancellables)
      }
   }
}


struct ArticlesScreen:View{
  @ObservedObject private(set) var viewModel:ArticlesViewModelWrapper

  var body:some View{
     VStack{
         AppBar()

         if viewModel.articlesState.loading{
            Loader()
         }

         if let error = viewModel.articlesState.error{
            ErrorMessage(message:error)
         }

         if(!viewModel.articlesState.articles.isEmpty){
            ScrollView{
                LazyVStack(spacing:10){
                    ForEach(viewModel.articlesState.articles, id: \.self){
                       article in ArticleItemView(article:article)
                    }
                }
            }
         }
     }.onAppear{
        self.viewModel.startObserving()
     }
  }
}

struct ArticleItemView:View{
    var article:Article
    var body:some View{
       VStack(alignment:.leading, spacing:8)
       {
          AsyncImage(url:URL(string:article.imageUrl))
          {
             phase in if phase.image != nil{
                phase.image!
                  .resizeable()
                  .aspectRatio(contentMode:.fit)
             }
              else if phase.error != nil {
                 Text("Image Load Error")
              }
               else{
                   PregressView()
               }
          }
          Text(article.title)
             .font(.title)
             .fontWeight(.bold)
          Text(article.description)
          Text(article.date).frame(maxWidth:.infinity, alignment: .trailing).foregroundStyle(.gray)
       }
       .padding(16)
    }
}

struct AppBar:View{
   var body: some View{
      Text("Articles")
        .font(.largeTitle)
        .fontWeight(.bold)
   }
}



struct Loader:View{
   var body: some View{
       ProgressView()
   }
}

struct ErrorMessage:View{
   var message:String

   var body:some View{
      Text(message)
        .font(.title)
   }
}
