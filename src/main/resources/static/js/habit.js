document.querySelector(".habitInput").addEventListener("keypress", function(event) {
  var habitDiv = document.querySelector('.habitCheck');
  var date = document.querySelector('.todayDate').value;
  var noneImg = document.querySelector('.noneImg-css');
  if (event.key === "Enter") {
    event.preventDefault();
    var content = document.querySelector(".habitInput").value;
    habitUpload(content,date, function (result) {
        var seq = result;
        if (seq > 0) {
            var htmlText =   '<div class="habitPosition ' + seq + '">' +
                             '<div class="form-check" id="habit' + seq +'">' +
                             '<div onclick="habitOn('+seq+')" class="div-inline habitDiv' + seq + '">' +
                             '<input class="form-check-input" type="checkbox" id="' + seq + 'checkbox-habit">' +
                             '<label class="form-check-label" id="' + seq + 'checkbox-habit">' +
                             '<p class="plan-p contentP habitP' + seq + '">' + content + '</p>' +
                             '</label></div>' +
                             '<input value="' + content + '" class="edit-input-css modify-input none-btn habit-input' + seq + '">' +
                             '<button onclick="habitModify( ' + seq + ' )" class="modify-btn none-btn edit-button-css">수정</button>' +
                             '<button onclick="habitDelete( ' + seq + ' )" class="delete-btn none-btn edit-button-css">삭제</button></div></div>';
            habitDiv.innerHTML += htmlText;
            document.querySelector(".habitInput").value = '';
            if(noneImg != null){
                            noneImg.remove();
                        }
        }
        });
  }
});