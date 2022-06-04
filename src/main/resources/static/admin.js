const imageUploader = document.getElementById('image-uploader');
const reader = new FileReader();
const imageWrapper = document.getElementById('product-image');

imageUploader.addEventListener('change', (event) =>{
    const file = event.target.files[0];
    console.log('file', file);
    reader.readAsDataURL(file);

    reader.addEventListener('load', (event) => {

        const img = document.createElement('img');
        imageWrapper.innerHTML = "";
        imageWrapper.appendChild(img);
        img.src = event.target.result;
        img.alt = file.name;
    });
})