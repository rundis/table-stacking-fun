(ns stacking-fun.devtools
  (:require [figwheel.client :as fw]))

(fw/start {:websocket-url "ws://localhost:3306/figwheel-ws"})
(enable-console-print!)

