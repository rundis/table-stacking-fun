(defproject animalia-auth-admin "0.1.0"
  :description "Animalia administrasjons app for brukertilganger"
  :url "https://github.com/animalia/animalia-auth-admin"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [hiccup "1.0.5"]
                 [org.clojure/clojurescript "0.0-3211"]
                 [cljsjs/react-with-addons "0.13.3-0"]
                 [quiescent "0.2.0-alpha1"
                  :exclusions [cljsjs/react-with-addons]]
                 [cljsjs/react-bootstrap "0.21.2-0"
                  :exclusions [org.webjars.bower/jquery
                               cljsjs/react-with-addons]]
                 [cljsjs/fixed-data-table "0.3.0-0"
                  :exclusions [cljsjs/react]]]


  :jvm-opts ["-Xmx768M"
             "-Djava.awt.headless=true"]
  :ring {:handler stacking-fun.web/app
         :port 4087}


  :profiles {
             :dev {:dependencies [[figwheel "0.3.5"]]
                   :plugins [[lein-cljsbuild "1.0.6"]
                             [lein-ring "0.8.13"]
                             [lein-figwheel "0.3.5"]]
                   :resource-paths ["resources" "target/cljsbuild"]
                   :test-paths ^:replace []
                   :cljsbuild {:builds {
                                        :dev
                                        {:source-paths ["src-cljs" "src-cljs-dev"]
                                         :figwheel {:on-jsload "stacking-fun.app/render"}
                                         :compiler {:output-dir "target/cljsbuild/public/js/out"
                                                    :output-to "target/cljsbuild/public/js/main.js"
                                                    :source-map true
                                                    :optimizations :none}}}}}

             :prod {:dependencies []
                    :plugins [[lein-cljsbuild "1.0.6"]
                              [lein-ring "0.8.13"]
                              ]
                    :hooks [leiningen.cljsbuild]
                    :resource-paths ["resources" "target/cljsbuild"]
                    :test-paths ^:replace []
                    :cljsbuild {:builds {
                                         :prod
                                         {:source-paths ["src-cljs"]
                                          :jar true
                                          :compiler {:output-dir "target/classes/public/js/outprod"
                                                     :output-to  "target/classes/public/js/main.js"
                                                     :source-map "target/classes/public/js/main.js.map"
                                                     :optimizations :advanced}}}}}}

  :figwheel {
    :http-server-root "public"
    :server-port 3306
    :css-dirs ["resources/public/css"]}
  )
