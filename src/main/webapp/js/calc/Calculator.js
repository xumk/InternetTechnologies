app.directive('calculator', calculator);
function calculator() {
    return {
        restrict: 'E',
        transclude: true,
        templateUrl: 'js/calc/calculator.html',
        scope: {
        },
        link: function (scope, el, attr, model) {
            scope.isOpen = false;
            scope.displayValue = null;
            scope.memoryExp = "";
            scope.result = 0;
            scope.operation = null;
            scope.isExp = false;
            scope.itemsList = [
                {'name': 'Обычный'},
                {'name': 'Инженерный'},
                {'name': 'Программист'}
            ];

            saveInMemory = function (operator) {
                if (!scope.isExp) {
                    if (scope.memoryExp) {
                        if (operator) {
                            scope.memoryExp += scope.displayValue + operator;
                        } else {
                            scope.memoryExp += scope.displayValue;
                        }
                    } else {
                        scope.memoryExp = scope.displayValue + operator;
                    }
                } else {
                    scope.memoryExp += operator + scope.displayValue;
                }
            };

            isNumeric = function (n) {
                return !isNaN(parseFloat(n)) && isFinite(n);
            };

            scope.onClickClear = function () {
                scope.operation = null;
                scope.memoryExp = "";
                scope.displayValue = null;
                scope.result = 0;
                scope.isExp = false;
            };
            scope.onClickDel = function () {
                if (scope.displayValue) {
                    str = scope.displayValue.toString();
                    scope.displayValue = str.slice(0, -1);
                }
            };
            scope.onClickSum = function () {
                if (scope.displayValue) {
                    saveInMemory("+");
                } else {
                    if (scope.memoryExp && isNumeric(scope.memoryExp.slice(-1))) {
                        scope.memoryExp += "+"
                    }
                }
                scope.displayValue = null;
            };
            scope.onClickSubtract = function () {
                if (scope.displayValue) {
                    saveInMemory("-");
                } else {
                    if (scope.memoryExp && isNumeric(scope.memoryExp.slice(-1))) {
                        scope.memoryExp += "-"
                    }
                }
                scope.displayValue = null;
            };
            scope.onClickMultiply = function () {
                if (scope.displayValue) {
                    saveInMemory("*");
                } else {
                    if (scope.memoryExp && isNumeric(scope.memoryExp.slice(-1))) {
                        scope.memoryExp += "*"
                    }
                }
                scope.displayValue = null;
            };
            scope.onClickDivide = function () {
                if (scope.displayValue) {
                    saveInMemory("/");
                } else {
                    if (scope.memoryExp && isNumeric(scope.memoryExp.slice(-1))) {
                        scope.memoryExp += "/"
                    }
                }
                scope.displayValue = null;
            };
            scope.onClickCalculate = function () {
                if (scope.displayValue) {
                    saveInMemory("");
                } else {
                    if (!scope.isExp) {
                        scope.memoryExp = scope.memoryExp.slice(0, -1);
                    }
                }
                scope.result = eval(scope.memoryExp);
                scope.displayValue = null;
                scope.isExp = true;
            };
            scope.updateIsOpen = function () {
                scope.isOpen = false;
            };
            scope.isOpenMenu = function () {
                scope.isOpen = !scope.isOpen;
            };
            scope.getIndexCalculatorTypr = function (index) {
                alert(index);
                scope.isOpen = false;
            };
            scope.addDisplayValue = function (value) {
                if (scope.displayValue) {
                    str = scope.displayValue.toString() + value;
                    scope.displayValue = str;
                } else {
                    scope.displayValue = value;
                }
            };
            scope.crateDecimal = function () {
                if (scope.displayValue) {
                    check = scope.displayValue.toString().split(",");
                    if (check.length < 2) {
                        check[0] += ".";
                        scope.displayValue = check[0];
                    }
                }
            };
            scope.rootExp = function () {
                if (scope.displayValue) {
                    scope.displayValue = Math.sqrt(parseFloat(scope.displayValue));
                } else {
                    if (scope.result) {
                        scope.result = Math.sqrt(parseFloat(scope.result));
                        scope.displayValue = scope.result;
                    }
                }
            };
            scope.oneDelValExp = function () {
                if (scope.displayValue) {
                    scope.displayValue = 1 / parseFloat(scope.displayValue);
                } else {
                    if (scope.result) {
                        scope.result = 1 / parseFloat(scope.result);
                        scope.displayValue = scope.result;
                    }
                }
            };
            scope.powTwoExp = function () {
                if (scope.displayValue) {
                    scope.displayValue = Math.pow(parseFloat(scope.displayValue), 2);
                } else {
                    if (scope.result) {
                        scope.result = Math.pow(parseFloat(scope.result), 2);
                        scope.displayValue = scope.result;
                    }
                }
            };
        }
    };
}
;