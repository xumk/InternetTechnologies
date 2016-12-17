app.controller('lb4', ['$scope', '$rootScope', function ($scope, $rootScope) {
        $rootScope.isNeedShowAnnonce = false;
        $rootScope.isShowMenu = false;
    }]);
app.controller('lb1', ['$scope', '$rootScope', '$anchorScroll', '$location',
    function ($scope, $rootScope, $anchorScroll, $location) {
        $rootScope.isNeedShowAnnonce = true;
        $rootScope.htmlTextAnnonce = "<h2>Инторесно чей рассказ?</h2>"
                + "<p>"
                + "<strong>Прочитай биографию автора:"
                + '<a href="https://ru.wikipedia.org/wiki/%D0%A7%D0%B5%D1%85%D0%BE%D0%B2,_%D0%90%D0%BD%D1%82%D0%BE%D0%BD_%D0%9F%D0%B0%D0%B2%D0%BB%D0%BE%D0%B2%D0%B8%D1%87">А. П. Чехов</a>'
                + "</strong><br>"
                + "Еще больше рассказов данного автора <br> "
                + "читайте на сайте"
                + " </p>"
                + '<p class="textright"><a href="http://ostrovok.de/old/classics/chekhov/">Сайт с рассказами &raquo;</a></p>';
        $scope.gotoAnchor = function (x) {
            var newHash = 'anchor_' + x;
            if ($location.hash() !== newHash) {
                // set the $location.hash to `newHash` and
                // $anchorScroll will automatically scroll to it
                $location.hash('anchor_' + x);
            } else {
                // call $anchorScroll() explicitly,
                // since $location.hash hasn't changed
                $anchorScroll();
            }
        };
    }]);
app.controller('lb2', ['$scope', '$rootScope', function ($scope, $rootScope) {
        $rootScope.isNeedShowAnnonce = true;
        $rootScope.htmlTextAnnonce = "<h2>Понравился язык?</h2>"
                + "<p>"
                + "<strong>Еще больше информации: "
                + '<a href="https://kotlinlang.org/">здесь</a>'
                + "</strong><br>"
                + "Все обучающие видо<br> "
                + "смотрите на канале"
                + " </p>"
                + '<p class="textright"><a href="https://www.youtube.com/channel/UCORRUYUmW1pffMgLPzU0XCA">Devcolibri &raquo;</a></p>';
    }]);
app.controller('lb3', ['$scope', '$rootScope', function ($scope, $rootScope) {
        $rootScope.isNeedShowAnnonce = true;
        $rootScope.htmlTextAnnonce = "<h2>Приглянулся фреймворк?</h2>"
                + "<p>"
                + "<strong>Вся информация: "
                + '<a href="https://angularjs.org/">здесь</a>'
                + "</strong><br>"
                + "Данные лабораторные работы выполнены<br> "
                + "с помощью 1.4.7 версии фреймворка"
                + " </p>"
                + '<p class="textright"><a href="https://www.youtube.com/playlist?list=PLvItDmb0sZw-9LXhYO9Ts8mToESWCr4e4">Видеокурс&raquo;</a></p>';
    }]);
