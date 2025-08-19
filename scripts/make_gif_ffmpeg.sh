#!/usr/bin/env bash
# make_gif_ffmpeg.sh
# Usage:
#   ./make_gif_ffmpeg.sh input.mp4 00:00:05 6 docs/media/demo-webdriver.gif
# Arguments:
#   $1 = input video (mp4, mkv, etc.)
#   $2 = start timestamp (HH:MM:SS)
#   $3 = duration in seconds
#   $4 = output GIF path
# Requires: ffmpeg

set -euo pipefail

IN="${1:-}"
START="${2:-00:00:00}"
DUR="${3:-5}"
OUT="${4:-docs/media/demo-webdriver.gif}"

if [ -z "$IN" ]; then
  echo "Usage: $0 <input.mp4> [start HH:MM:SS] [duration s] [out gif]" >&2
  exit 1
fi

TMP_PAL=$(mktemp /tmp/palette.XXXX.png)
ffmpeg -y -ss "$START" -t "$DUR" -i "$IN" -vf "fps=12,scale=960:-1:flags=lanczos,palettegen" "$TMP_PAL"
ffmpeg -y -ss "$START" -t "$DUR" -i "$IN" -i "$TMP_PAL" -lavfi "fps=12,scale=960:-1:flags=lanczos[x];[x][1:v]paletteuse" "$OUT"
echo "GIF written to $OUT"
