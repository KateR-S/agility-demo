(ns agility-demo.datomb
  (:require [shadow.resource :as rc]))

(def initial-entries (rc/inline "./entries.json"))
(def initial-users (rc/inline "./users.json"))

;(defn parse-map
  ;[data]
  ;(str data)
  ;(->> data
  ;     (.parse js/JSON)
  ;     (js->clj)
  ;     )
  ;)

(defn parse-map
  [data]
  (let [a (.parse js/JSON data)]
    (-> a
        (js->clj :keywordize-keys true))))

(defn add
  [a data]
  (if (vector? data)
    (concat a data)
    (cons data a)))

(defn delete
  [a id]
  (remove #(= (:id %) id) a))
