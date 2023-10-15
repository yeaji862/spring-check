document.querySelector(".habitInput").addEventListener("keypress", function(event) {
  var habitDiv = document.querySelector('.habitCheck');
  var date = document.querySelector('.todayDate').value;
  var noneImg = document.querySelector('.noneImg-css');
  if (event.key === "Enter") {
    event.preventDefault();
    var content = document.querySelector(".habitInput").value;
    habitUpload(content, function (result) {
        var seq = result;
        if (seq > 0) {
            var htmlText =  '<div class="habitPosition'+seq+'">' +
                            '<div class="form-check" id="habit'+seq+'" onclick="habitOn('+seq+')">' +
                            '<input class="form-check-input" type="checkbox" id="'+seq+'checkbox-habit">' +
                            '<label class="form-check-label" id="'+seq+'checkbox-habit">' +
                            '<p class="plan-p">'+content+'</p>' +
                            '</label></div></div>';
            habitDiv.innerHTML += htmlText;
            content = '';
            if(noneImg != null){
                            noneImg.remove();
                        }
        }
        });
  }
});