(ns agility-demo.styles
  (:require [re-frame.core :as re-frame]))

(defn header-footer
  [text]
  [:div.text-center
   {:style
    {:background-color "#000"
     :color            "#fff"}}
   text])

(defn navigation-footer
  [items]
  [:div.text-center
   {:style
    {:background-color "#666"}}
   items])

(def central-container
  {:style
   {:background-color "#ddd"
    :max-width        "20%"
    :min-width        "15%"}})

(defn single-button
  [dispatch text]
  [:button.btn-block {:on-click #(re-frame/dispatch dispatch)} text])

(defn small-button
  [dispatch text]
  [:button {:on-click #(re-frame/dispatch dispatch)} text])