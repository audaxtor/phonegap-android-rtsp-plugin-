```html
<script>
function play(){
	rtsp.play([document.getElementById("uri").value]);
}
</script>
<input type="text" id="uri" value="rtsp://123456789"/>
<button onclick="play()">play</button>
```

```bash
phonegap platform add android
phonegap plugin add https://github.com/qwIvan/phonegap-android-rtsp-plugin-.git
phonegap run
```
