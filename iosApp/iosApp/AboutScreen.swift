//
//  AboutScreen.swift
//  iosApp
//
//  Created by Satyam Singh on 11/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct AboutScreen: View {
    var body: some View {
        NavigationStack {
            AboutDeviceView().navigationTitle("About Device")
        }

    }
}

#Preview {
    AboutScreen()
}
