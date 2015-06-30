(ns stacking-fun.web
  (:require [stacking-fun.index-view :as index]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.resource :refer [wrap-resource]]))



(defroutes app-routes
  (GET "/" [] index/show))

(def app
  (-> app-routes
      (wrap-resource "META-INF/resources")
      (wrap-resource "/cljsjs/production")
      (wrap-defaults site-defaults)))
