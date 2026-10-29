# Bingo Dice AutoPlay starter

Phone-only cloud build steps:
1. Create a PRIVATE GitHub repository named `bingo-dice-autoplay` at https://github.com/new.
2. Upload the extracted contents of this ZIP to the repository root. Use Chrome's Desktop site if folder upload is unavailable.
3. Open the repository Actions tab and enable Actions if prompted.
4. Select `Build Bingo Dice Android APK`, then tap `Run workflow`.
5. When complete, open the run and download the `bingo-dice-starter-apk` artifact. Extract it and install the APK.

Important: this starter app is NOT a working bot yet. The app builds an overlay scaffold and includes the move-selection engine, but live screen recognition, taps, turn detection, and ad recovery are not implemented. Do not use it unattended. No GitHub token or third-party build service is required; GitHub Actions builds within your repository.
