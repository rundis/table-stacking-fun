(ns stacking-fun.app
  (:require [quiescent.core :as q]
            [quiescent.dom :as d]
            [react-bootstrap.components :as rb]
            [fixed-data-table.components :as fdt]))


(defn gen-table
  "Generate `size` rows vector of 4 columns vectors to mock up the table."
  [size]
  (mapv (fn [i] [i                                                   ; Number
                 (rand-int 1000)                                     ; Amount
                 (rand)                                              ; Coeff
                 (rand-nth ["Here" "There" "Nowhere" "Somewhere"])]) ; Store
        (range 1 (inc size))))

(def app-state (atom {:table (gen-table 1000)}))

(defn getter [k row] (get row k))



(defn dropdown-renderer [props]
  (rb/DropdownButton {:title "Select"
                      :bsSize "small"}
                     (rb/MenuItem {:eventKey 1
                                   :href "#"}
                                  "Selection 1")
                     (rb/MenuItem {:eventKey 2
                                   :href "#"}
                                  "Selection 2")))


(q/defcomponent Table
  [{:keys [table]}]
  (fdt/Table {:width 600
              :height 400
              :rowHeight 50
              :rowGetter #(get table %)
              :rowsCount (count table)
              :headerHeight 50}
             (fdt/Column {:label "Col1"
                          :dataKey 0
                          :cellDataGetter getter
                          :cellRenderer dropdown-renderer
                          :width 150})
             (fdt/Column {:label "Col2"
                          :dataKey 1
                          :cellDataGetter getter
                          :width 150})
             (fdt/Column {:label "Col3"
                          :dataKey 2
                          :cellDataGetter getter
                          :width 150})
             (fdt/Column {:label "Col4"
                          :dataKey 3
                          :cellDataGetter getter
                          :width 150})))


(defn render
  [props]
  (q/render (Table @app-state)
            (.getElementById js/document "app-content")))



(defn ^:export main
  "Application entry point"
  []
  (render {}))
