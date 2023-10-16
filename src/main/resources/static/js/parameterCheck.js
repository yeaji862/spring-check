     function checkParameter() {
        const urlParams = new URL(location.href).searchParams;
        const paramValue = urlParams.get('status');
        if (paramValue === "fail") {
                var errorElement = document.querySelector(".error");
                errorElement.style.display = "block";
        }else if(paramValue === "pass"){
                alert("비밀번호가 변경되었습니다.");
        }else if(paramValue === "link"){
                alert("유효하지 않은 링크입니다.");
        }else if(paramValue === "join_fail"){
                alert("회원가입 실패 다시 시도해주세요.");
        }
     }

     window.onload = checkParameter;