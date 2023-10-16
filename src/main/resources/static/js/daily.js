document.querySelector(".dailyInput").addEventListener("keypress", function(event) {
  var dailyDiv = document.querySelector('.dailyCheck');
  var date = document.querySelector('.todayDate').value;
  var noneImg = document.querySelector('.noneImg-css');
  if (event.key === "Enter") {
    event.preventDefault();
    var content = document.querySelector(".dailyInput").value;
    dailyUpload(content,date, function (result) {
        var seq = result;
        if (seq > 0) {
            var htmlText =   '<div class="dailyPosition ' + seq + '">' +
                             '<div class="form-check" id="daily' + seq +'">' +
                             '<div onclick="dailyOn('+seq+')" class="div-inline dailyDiv' + seq + '">' +
                             '<input class="form-check-input" type="checkbox" id="' + seq + 'checkbox-daily">' +
                             '<label class="form-check-label" id="' + seq + 'checkbox-daily">' +
                             '<p class="plan-p contentP dailyP' + seq + '">' + content + '</p>' +
                             '</label></div>' +
                             '<input value="' + content + '" class="edit-input-css modify-input none-btn daily-input' + seq + '">' +
                             '<button onclick="dailyModify( ' + seq + ' )" class="modify-btn none-btn edit-button-css">수정</button>' +
                             '<button onclick="dailyDelete( ' + seq + ' )" class="delete-btn none-btn edit-button-css">삭제</button></div></div>';
            dailyDiv.innerHTML += htmlText;
            document.querySelector(".dailyInput").value = '';
            if(noneImg != null){
                noneImg.remove();
            }
        }
    });
  }
});