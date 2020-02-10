(ns agility-demo.subs
  (:require
    [re-frame.core :as re-frame]
    [agility-demo.utils :as utils]))

(re-frame/reg-sub
  ::this
  (fn [db]
    (:this db)))

(re-frame/reg-sub
  ::all
  (fn [db]
    (:all db)))

(re-frame/reg-sub
  ::tmp
  (fn [db]
    (:tmp db)))

(re-frame/reg-sub
  ::active-handler
  (fn [db _]
    (get-in db [:active :id])))

(re-frame/reg-sub
  ::active-handler-details
  (fn [db _]
    (utils/retrieve-user-details db)))

(re-frame/reg-sub
  ::active-panel
  (fn [db _]
    (get-in db [:active :panel])))

(re-frame/reg-sub
  ::active-nav
  (fn [db _]
    (get-in db [:active :nav])))

(re-frame/reg-sub
  ::active-class
  (fn [db _]
    (get-in db [:active :class])))

(re-frame/reg-sub
  ::active-class-details
  (fn [db _]
    (utils/retrieve-class-details db)))

(re-frame/reg-sub
  ::active-class-string
  (fn [db _]
    (-> db
        (utils/retrieve-class-details)
        (utils/class->header-string))))

(re-frame/reg-sub
  ::active-progress-string
  (fn [db _]
    "Med: 11-44 (R16,W15,L1)"
    ))