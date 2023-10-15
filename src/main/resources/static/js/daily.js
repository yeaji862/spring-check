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
            var htmlText =  '<div class="dailyPosition'+seq+'">' +
                            '<div class="form-check" id="daily'+seq+'" onclick="dailyOn('+seq+')">' +
                            '<input class="form-check-input" type="checkbox" id="'+seq+'checkbox-daily">' +
                            '<label class="form-check-label" id="'+seq+'checkbox-daily">' +
                            '<p class="plan-p">'+content+'</p>' +
                            '</label></div></div>';
            dailyDiv.innerHTML += htmlText;
            content = '';
            if(noneImg != null){
                noneImg.remove();
            }
        }
    });
  }
});