window.onload=function () {
    setInterval(function () {
        var currentTime=new Date();
        var hour=currentTime.getHours();
        hour=hour<10?"0"+hour:hour;
        var minute=currentTime.getMinutes();
        minute=minute<10?"0"+minute:minute;
        var seconds=currentTime.getSeconds();
        seconds=seconds<10?"0"+seconds:seconds;
        $(".timeBox").text(hour+":"+minute+":"+seconds)
            .css("fontSize","15px")
            .css("color","red")
    },1000);
}