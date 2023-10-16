       window.onpageshow = function(event) {
       alert(session.userNum);
        if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
            location.href="/user";
            }
        }