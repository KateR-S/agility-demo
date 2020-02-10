(ns agility-demo.views
  (:require
    [re-frame.core :as re-frame]
    [agility-demo.subs :as subs]
    [agility-demo.events :as events]
    [agility-demo.utils :as utils]
    [agility-demo.styles :as styles]))

(defn start-panel []
  [:div
   [:h1 "Ring Party Tablet - Ring 1"]
   [:p [:button {:on-click (fn [] (re-frame/dispatch [::events/populate-classes])
                             (re-frame/dispatch [::events/populate-users]))} "Startup"]]
   [:p [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :selector])} "Use the System"]]])

(defn get-id
  [id handler]
  [:input {:id      id
           :handler handler}])

(defn control-panel []
  (let [tmp          (re-frame/subscribe [::subs/tmp])
        active-class (re-frame/subscribe [::subs/active-class])]
    [:div
     [:input {:value    (:ro @tmp)
              :readOnly true}]
     [:h2 "Enter Running Order/ Plaza Code"]
     [:div.btn-group
      [:button {:on-click #(re-frame/dispatch [::events/delete :all])} "Clear"]
      [:button {:on-click #(re-frame/dispatch [::events/append-value "7"])} "7"]
      [:button {:on-click #(re-frame/dispatch [::events/append-value "8"])} "8"]
      [:button {:on-click #(re-frame/dispatch [::events/append-value "9"])} "9"]]
     [:p
      [:button {:on-click #(re-frame/dispatch [::events/delete :last])} "Del"]
      [:button {:on-click #(re-frame/dispatch [::events/append-value "4"])} "4"]
      [:button {:on-click #(re-frame/dispatch [::events/append-value "5"])} "5"]
      [:button {:on-click #(re-frame/dispatch [::events/append-value "6"])} "6"]]
     [:p
      [:button {:on-click #(re-frame/dispatch [::events/append-value "0"])} "0"]
      [:button {:on-click #(re-frame/dispatch [::events/append-value "1"])} "1"]
      [:button {:on-click #(re-frame/dispatch [::events/append-value "2"])} "2"]
      [:button {:on-click #(re-frame/dispatch [::events/append-value "3"])} "3"]]
     [:p
      [:button {:on-click (fn []
                            (re-frame/dispatch [::events/store-id])
                            (re-frame/dispatch [::events/find-handler])
                            (re-frame/dispatch [::events/set-active-panel :options]))} "OK"]]
     [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :selector])} "Back"]]))

(defn scrime-panel []
  [:div
   [:h1 "SCRIME"]
   [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :selector])} "Back"]])

(defn manage-class-panel []
  [:div
   [:h1 "MANAGE CLASS"]
   [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :selector])} "Back"]])

(defn info-panel []
  [:div
   [:h1 "INFO"]
   [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :selector])} "Back"]])

(defn options-panel []
  (let [handler-details (re-frame/subscribe [::subs/active-handler-details])]
    [:div.container-fluid
     [:div
      [:h1 (:handler @handler-details)]
      [:h2 (:dog @handler-details)]]
     [:div.row
      [:div.col-xs-4.col-md-3
       [:p "R/O"]
       [:p (:id @handler-details)]
       ]
      [:div.col-xs-4.col-md-3
       [:p "Height"]
       [:p (:height @handler-details)]
       ]
      [:div.col-xs-4.col-md-3
       [:p "Grade"]
       [:p (:grade @handler-details)]
       ]
      [:div.col-xs-4.col-md-3
       [:p "Status"]
       [:p "xxx"]]]
     [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :selector])} "Back"]]))

(defn selector []
  [:div.container
   styles/central-container
   (styles/single-button [::events/set-active-panel :control] "Control Ring")
   (styles/single-button [::events/set-active-panel :scrime] "Scrime")
   (styles/single-button [::events/set-active-panel :manage] "Manage Class")
   (styles/single-button [::events/set-active-panel :message] "PA Message")
   (styles/single-button [::events/set-active-panel :ring] "View Ring Plan")
   (styles/single-button [::events/set-active-panel :times] "Enter Course Times")])

(defn exit-panel []
  (styles/small-button [::events/set-active-panel :start] "Exit"))

(defn main-panel []
  (let [active        (re-frame/subscribe [::subs/active-panel])
        active-nav    (re-frame/subscribe [::subs/active-nav])
        class-details (re-frame/subscribe [::subs/active-class-string])
        progress      (re-frame/subscribe [::subs/active-progress-string])]
    (fn []
      [:div
       (styles/header-footer @class-details)
       (condp = @active
         :start [start-panel]
         :selector [selector]
         :control [control-panel]
         :scrime [scrime-panel]
         :manage [manage-class-panel]
         :message [info-panel]
         :ring [info-panel]
         :times [info-panel]
         :options [options-panel])
       (styles/navigation-footer
         (condp = @active-nav
           :exit [exit-panel]))
       (styles/header-footer @progress)])))
