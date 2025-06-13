//
//  AboutScreen.swift
//  iosApp
//
//  Created by Satyam Singh on 11/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    
    @Environment(\.dismiss)
    private var dismiss
    
    var body: some View {
        NavigationStack {
            AboutDeviceView()
                .toolbar {
                    ToolbarItem(placement: .principal) {
                        Text("About Device")
                            .font(.headline)
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

#Preview {
    AboutScreen()
}
