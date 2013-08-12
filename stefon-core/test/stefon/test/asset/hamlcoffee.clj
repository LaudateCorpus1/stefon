(ns stefon.test.asset.hamlcoffee
  (:require [stefon.test.helpers :as h]
            [stefon.asset.hamlcoffee :as hc]
            [stefon.util :refer (dump)])
  (:use clojure.test))

(defn wrap [name output]
  "The hamlcoffee compiler wraps the code, so we need to wrap our expected output"
  (str "(function() {\n  var _ref;\n\n  if ((_ref = window.HAML) == null) {\n    window.HAML = {};\n  }\n\n  window.HAML['"
       name
       "'] = function(context) {\n    return (function() {\n      var $o;\n      $o = [];\n      $o.push(\""

       output
       "\");\n      return $o.join(\"\\n\");\n    }).call(context);\n  };\n\n}).call(this);\n"))

(deftest test-basic
  (h/test-expected "test/fixtures/assets"
                   "javascripts/basic.hamlc"
                   "/assets/javascripts/basic"
                   [(wrap "basic"
                          "<!DOCTYPE html>\\n<html>\\n  <head>\\n    <title>\\n      Title\\n    </title>\\n  </head>\\n  <body>\\n    <h1>\\n      Header\\n    </h1>\\n  </body>\\n</html>")]))

;; (deftest bad-haml-syntax
;;   (h/test-syntax "test/fixtures/assets"
;;                  "javascripts/badhaml1.hamlc"
;;                  ["ERROR: Syntax Error on line 1"]))

;;   (is (has-text?
;;        (preprocess-hamlcoffee
;;         (io/file "test/fixtures/assets/javascripts/badhaml2.hamlc"))
;;        "@import \"includeme.less\"")))

;; (testing "bad coffee syntax"
;;   (is (has-text?
;;        (preprocess-hamlcoffee
;;         (io/file "test/fixtures/assets/javascripts/badcoffee.hamlc"))
;;        "@import \"includeme.less\""))))