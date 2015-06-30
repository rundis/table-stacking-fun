(ns stacking-fun.index-view
  (:require [hiccup.page :as page]
            [hiccup.core :refer [h]]
            [clojure.data.json :as json]))


(defn include-page-scripts [sources]
  (map #(page/include-js %) sources))

(defn include-page-styles [sources]
  (map #(page/include-css %) sources))

(defn page-scriptlets [scriptlets]
  (map #(vector :script %) scriptlets))

(defn page-data [req]
  (json/write-str {:bruker (:bruker req)}))

(defn- minified-mode? []
  (clojure.java.io/resource "public/js/main.js.map"))

(defn show [req]
  (let [minified? (minified-mode?)]
    (page/html5
     [:head
      (include-page-styles
       ["webjars/bootstrap/3.3.4/dist/css/bootstrap.css"
        "/fixed-data-table.min.inc.css"
        "/css/index.css"])
      [:title "Table stacking fun"]]
     [:body
      [:div {:id "app-content"}
       [:div.outer
        [:div.middle
         [:div.inner
          [:p "Vennligst vent mens applikasjonen laster"]
          [:img {:src "/images/loader.gif"}]]]]]
      (include-page-scripts
       (if minified?
         ["/js/main.js"]
         ["/js/out/goog/base.js"
          "/js/main.js"]))
      (page-scriptlets
       (if minified?
         [(str "stacking_fun.app.main(" (page-data req) ")")]
         ["goog.require('stacking_fun.app')"
         "goog.require('stacking_fun.devtools')"
         "stacking_fun.app.main()"]))])))

