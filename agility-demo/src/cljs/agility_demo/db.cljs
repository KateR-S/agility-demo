(ns agility-demo.db
  (:require [agility-demo.datomb :as file]))

(def default-db
  {:this   {:handler {:id      0
                      :handler "handler name"}
            :class   {:id     0
                      :type   "N/A"
                      :grades "0-0"}
            :entries []}
   :tmp    {:ro ""}
   :active {:panel :start
            :class 1
            :id 0
            :handler-details "xyz"
            :nav :exit}})