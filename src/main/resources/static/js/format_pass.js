function validatePassword(password) {
    const passwordPattern = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,}$/;
    return passwordPattern.test(password);
}

document.getElementById('passWord').addEventListener('blur', function() {
        var passError = document.querySelector('.passError');
        var passWord = document.getElementById('passWord').value;
            if (!validatePassword(passWord)) {
                passError.style.display = 'block';
                event.preventDefault();
            }else{
                passError.style.display = 'none';
            }
        });

document.getElementById('checkPassWord').addEventListener('blur', function() {
        matchPass();
        });

function matchPass(){
        var passWord = document.getElementById('passWord').value;
        var checkPassWord = document.getElementById('checkPassWord').value;
        var matchError = document.querySelector('.matchError');

        if(passWord != checkPassWord){
            matchError.style.display = 'block';
            event.preventDefault();
        }else{
            matchError.style.display = 'none';
        }
}