## AI API 
Neural love (Image AI) :

### Container
```
podman run -d --name img-gen-ln -p 8086:8086 -e external.iaimgapi.token='yourtoken' registry.gitlab.com/js-asi2/asi2-resources/img-gene-neural-love-api:v1.1
```
### Test :
url:  
```
http://localhost:8080/prompt/req
```
body:
```
{
    "promptTxt":"a cute lama ninja, pixar style",
    "negativePromptTxt":""
}
```



## Authors
- Marin Thomas
- Rtayli Oumaima
- Tran Van Ba Rémi
- Al-Homsi Raghad

