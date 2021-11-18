function some1() {
    return s;
}

function some2() {
    some1(some2(some1()));
}

var s = some2();
