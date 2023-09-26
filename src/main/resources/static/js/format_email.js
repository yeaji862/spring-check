function validateEmail(email) {
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailPattern.test(email);
}

document.getElementById('email').addEventListener('blur', function() {
        var email = document.getElementById('email').value;
        var duplicateError = document.querySelector('.duplicateError'); // 회원가입시 이메일 존재 여부
        var noneMail = document.querySelector('.noneMail'); // 비밀번호 변경시 이메일 존재 여부

                findId(email, function (result) {
                  if (result) {
                    duplicateError.style.display = 'block';
                  }else{
                    duplicateError.style.display = 'none';
                  }
                });

                 findId(email, function (result) {
                    if (result) {
                        noneMail.style.display = 'none';
                    }else{
                        noneMail.style.display = 'block';
                    }
                  });

        });

document.querySelector('.email').addEventListener('blur', function() {
        var email = document.getElementById('email').value;
        var mailError = document.querySelector('.mailError');

       if (!validateEmail(email)) {
            mailError.style.display = 'block';
       }else{
            mailError.style.display = 'none';
       }
        });