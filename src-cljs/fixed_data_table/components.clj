(ns fixed-data-table.components)


(defn tag-definition
  "Return a form to defining a wrapper function for a React Bootstrap tag
  component."
  [tag]
  (let [f (symbol (str "js/FixedDataTable." (name tag)))]
    `(defn ~tag [& args#]
       ~(str "Return a component for ")
       (let [a# (make-array 0)]
         (.push a# (cljs.core/clj->js (first args#)))
         (doseq [arg# (rest args#)] (.push a# arg#))
         (.apply (.createFactory js/React ~f) nil a#)))))


(defmacro define-tags
  "Macro which expands to a do block which contains a defmacro for
  each supported React Bootstrap tag. The resulting macros take
  an (optional) properties argument, and any number of child
  arguments. The properties argument may be a Clojure map or a JS
  object."
  [& tags]
  `(do (do ~@(clojure.core/map tag-definition tags))
       (def ~'defined-tags
         ~(zipmap (map (comp keyword name) tags)
                 tags))))
