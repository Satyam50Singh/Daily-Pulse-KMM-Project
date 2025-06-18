//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Satyam Singh on 18/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct SourcesScreen: View {
    
    @Environment(\.dismiss)
    private var dismiss
    
    var body: some View {
        NavigationStack {
            SourcesContentView()
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

struct SourcesContentView:View {
    var body: some View {
        Text("Sources data will be available soon.")
    }
}

#Preview {
    SourcesScreen()
}
