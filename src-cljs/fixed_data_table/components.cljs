(ns fixed-data-table.components
  (:refer-clojure :exclude [time map meta])
  (:require-macros [fixed-data-table.components :refer [define-tags]]
                   [quiescent.dom :as dm])
  (:require (cljsjs.fixed-data-table)))

(define-tags
  Table Column ColumnGroup)
