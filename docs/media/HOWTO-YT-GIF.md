# Récupérer la vidéo YouTube en local (optionnel)

Si vous possédez les droits nécessaires, vous pouvez récupérer votre vidéo YouTube en local pour générer un GIF court :
```bash
# Installer yt-dlp (Linux/macOS) :
#   pip install yt-dlp
# ou
#   brew install yt-dlp

# Télécharger en mp4 (ex. 720p si dispo)
yt-dlp -f "mp4" -o demo.mp4 "https://youtu.be/GXgP2HaZ0E8"

# Générer un GIF de 6s à partir de 00:00:05
bash scripts/make_gif_ffmpeg.sh demo.mp4 00:00:05 6 docs/media/demo-webdriver.gif
```
**Bonnes pratiques portfolio :** un GIF de **5–8 s**, largeur **≤ 960 px**, **12 fps** et palette optimisée.
