# make_gif_ffmpeg.ps1
param(
  [Parameter(Mandatory=$true)][string]$Input,
  [string]$Start = "00:00:00",
  [int]$Duration = 5,
  [string]$Out = "docs/media/demo-webdriver.gif"
)
$Palette = [System.IO.Path]::GetTempFileName().Replace(".tmp",".png")
ffmpeg -y -ss $Start -t $Duration -i $Input -vf "fps=12,scale=960:-1:flags=lanczos,palettegen" $Palette
ffmpeg -y -ss $Start -t $Duration -i $Input -i $Palette -lavfi "fps=12,scale=960:-1:flags=lanczos[x];[x][1:v]paletteuse" $Out
Write-Host "GIF written to $Out"
