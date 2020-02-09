(ns agility-demo.events
  (:require
    [re-frame.core :as re-frame]
    [agility-demo.db :as db]
    [agility-demo.datomb :as datomb]
    [clojure.string :as str]
    [agility-demo.utils :as utils]
    ))

(re-frame/reg-event-db
  ::initialize-db
  (fn [_ _]
    db/default-db))

(re-frame/reg-event-db
  ::set-active-panel
  (fn [db [_ value]]
    (assoc-in db [:active :panel] value)))

(re-frame/reg-event-db
  ::populate-classes
  (fn [db _]
    (assoc db :classes (datomb/parse-map datomb/initial-entries))))

(re-frame/reg-event-db
  ::populate-users
  (fn [db _]
    (assoc db :users (datomb/parse-map datomb/initial-users))))

(defn append [db val loc]
  (->> val
       (str (get-in db loc))
       (assoc-in db loc)))

(defn delete [db c loc]
  (case c
    :all (assoc-in db loc "")
    :last (assoc-in db loc (->> (get-in db loc)
                                (drop-last)
                                (str/join "")))))

(re-frame/reg-event-db
  ::append-value
  (fn [db [_ k]] (append db k [:tmp :ro])))

(re-frame/reg-event-db
  ::delete
  (fn [db [_ k]] (delete db k [:tmp :ro])))

(re-frame/reg-event-db
  ::store-id
  (fn [db _]
    (assoc-in db [:active :handler]
              (get-in db [:tmp :ro]))))

(re-frame/reg-event-db
  ::find-handler
  (fn [db _]
    (assoc-in db [:active :handler-details]
              (utils/retrieve-user-details db))))