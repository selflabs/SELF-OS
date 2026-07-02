import SwiftUI

struct PreviewDisclaimer: View {
    var body: some View {
        Text("Preview only. No real earnings are generated in SELF OS Personal Intelligence (Community Edition).")
            .font(.caption)
            .fontWeight(.medium)
            .foregroundStyle(.orange)
    }
}

struct MockPreviewBanner: View {
    var body: some View {
        Text("Mock / Preview Only — not connected to SELF OS Core.")
            .font(.caption)
            .foregroundStyle(.blue)
    }
}
