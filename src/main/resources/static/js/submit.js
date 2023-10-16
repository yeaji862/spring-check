function signUpSubmit(){
    var duplicateError = $('.duplicateError').css('display');
    var mailError = $('.mailError').css('display');
    var passError = $('.passError').css('display');
    var matchError = $('.matchError').css('display');
    var email = document.getElementById('email').value;
    var passWord = document.getElementById('passWord').value;
    var checkPassWord = document.getElementById('checkPassWord').value;

    if(email === '' || passWord === '' || checkPassWord === ''){
        alert('빈칸을 채워주세요');
        event.preventDefault();
    }else if(duplicateError === 'block' || mailError === 'block' || passError === 'block' || matchError === 'block'){
        event.preventDefault();
    }
}
function linkSand(){
    var email = document.getElementById('email').value;
    var noneMail = $('.noneMail').css('display');
    var success = document.querySelector('.success');
    var spinner = document.querySelector('.spinner');
    var findPass_button = document.querySelector('.findPass_button');

    if(noneMail === 'none'){
          spinner.style.display = 'block';
          findPass_button.style.display = 'none';
          sandMail(email, function (result) {
            if (result) {
                spinner.style.display = 'none';
                success.style.display = 'block';
            }else{
                spinner.style.display = 'none';
                findPass_button.style.display = 'block';
            }

            });
    }
}

function editPass(){
    var passError = $('.passError').css('display');
    var matchError = $('.matchError').css('display');
    var passWord = document.getElementById('passWord').value;
    var checkPassWord = document.getElementById('checkPassWord').value;

    if(passWord === '' || checkPassWord === ''){
        alert('빈칸을 채워주세요');
        event.preventDefault();
    }else if(passError === 'block' || matchError === 'block'){
        event.preventDefault();
    }
}

function resizeAndSubmit() {
    const imageInput = document.getElementById('file');
    if (imageInput.files.length > 0) {
        const file = imageInput.files[0];
        const reader = new FileReader();

        reader.onload = function (e) {
            const img = new Image();
            img.src = e.target.result;

            img.onload = function () {
                const canvas = document.getElementById('resizedCanvas');
                const ctx = canvas.getContext('2d');
                const maxWidth = 120;
                const maxHeight = 120;

                // 이미지 크기를 60x60 픽셀로 조절
                let width = img.width;
                let height = img.height;

                if (width > height) {
                    if (width > maxWidth) {
                        height *= maxWidth / width;
                        width = maxWidth;
                    }
                } else {
                    if (height > maxHeight) {
                        width *= maxHeight / height;
                        height = maxHeight;
                    }
                }

                canvas.width = width;
                canvas.height = height;

                ctx.drawImage(img, 0, 0, width, height);

                // 조절된 이미지를 base64로 가져옴
                const resizedImageData = canvas.toDataURL('image/jpeg');

                // FormData를 생성하고 조절된 이미지를 추가
                const formData = new FormData();
                formData.append('resizedImage', dataURLtoBlob(resizedImageData));

                // FormData를 서버로 전송 (fetch API 사용)
                fetch('/user/img', {
                    method: 'POST',
                    body: formData
                })
                .then(response => {
                    if (response.ok) {
                        window.location.href="/check/view?date=" + today();
                    } else {
                        alert('다시 시도해주세요');
                    }
                })
                .catch(error => {
                    console.error('네트워크 오류: ' + error);
                });
            };
        };

        reader.readAsDataURL(file);
    }else{
        alert('이미지를 업로드 해주세요.');
    }
}

        // Data URL을 Blob로 변환
        function dataURLtoBlob(dataURL) {
            const parts = dataURL.split(',');
            const byteString = atob(parts[1]);
            const mimeString = parts[0].split(':')[1].split(';')[0];

            const arr = new Uint8Array(byteString.length);
            for (let i = 0; i < byteString.length; i++) {
                arr[i] = byteString.charCodeAt(i);
            }

            return new Blob([arr], { type: mimeString });
        }