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