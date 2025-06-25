javascript: function generateScript(matkulArray) {
    var array = []; for (var i = 0; i < matkulArray.length; i++) {
        var
            array1 = []; for (var j = 0; j < matkulArray[i][1].length; j++) {
                array1.push(document.getElementsByName(matkulArray[i][0])[matkulArray[i][1][j]]);
            }; array.push(array1);
    }; return
    array;
}; function checkCapacity(item) {
    var
        registered = item.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling.innerHTML;
    var capacity = item.parentElement.nextElementSibling.nextElementSibling.nextElementSibling.innerHTML; if
        (parseInt(capacity) <= parseInt(registered)) return false; return true;
}; var radioArray = generateScript([
    ['c[CSGE601021_01.00.12.01-2024]', [2]],
    ['c[CSCM601213_01.00.12.01-2024]', [0]],
    ['c[CSGE601011_06.00.12.01-2024]', [0, 5]],
    ['c[CSCM601252_01.00.12.01-2024]', [1, 2]],
    ['c[UIGE600007_01.00.12.01-2024]', [4]],]); for (var i = 0; i <
        radioArray.length; i++) {
    var flag = true; for (var j = 0; j < radioArray[i].length; j++) {
        if
            (checkCapacity(radioArray[i][j])) { radioArray[i][j].click(); flag = false; break; }
    }; if (flag) {
        window.alert('Semua kelas pilihan di ' + radioArray[i][0].getAttribute(' name')
            + ' tidak memiliki kapasitas, tolong pilih secara manual.');
    };
};