(ns agility-demo.views
  (:require
    [re-frame.core :as re-frame]
    [agility-demo.subs :as subs]
    [agility-demo.events :as events]
    [agility-demo.utils :as utils]))

(defn get-id
  [id handler]
  [:input {:id      id
           :handler handler}])

(defn control-panel []
  (let [tmp          (re-frame/subscribe [::subs/tmp])
        active-class (re-frame/subscribe [::subs/active-class])]
    [:div
     [:input {:value (:ro @tmp)
              :readOnly true}]
     [:h2 "Enter Running Order/ Plaza Code"]
     [:h3]
     [:p
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
     ;[:div.pb-1
     ; [:h1 (:handler @handler-details)]
     ; [:h2 (:dog @handler-details)]]
     [:div.row
      [:div.col-xs-6.col-md-4
       [:p "A"]
       ]
      [:div.col-xs-6.col-md-4
       [:p "A"]
       ]
      [:div.col-xs-6.col-md-4
       [:p "A"]
       ]]
     ;[:div.row
     ; [:div.col-sm
     ;  [:p "B"]
     ;  ]
     ; [:div.col-sm
     ;  [:p "B"]
     ;  ]
     ; [:div.col-sm
     ;  [:p "B"]
     ;  ]
     ;]
     ;[:grid-column-start "RO"]
     ;[:grid-column-start "AB"]
     ;[:grid-column-gap]
     ;[:grid-column-start "XY"]
     ;]
     ;[:body
     ; [:table
     ;  [:tr
     ;   [:td "R/O"]
     ;   [:td "Height"]
     ;   [:td "Grade"]
     ;   [:td "Status"]
     ;   ]
     ;  [:tr
     ;   [:td (:id @handler-details)]
     ;   [:td (:height @handler-details)]
     ;   [:td (:grade @handler-details)]
     ;   ]]


     [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :selector])} "Back"]]))

(defn selector []
  [:div
   [:p [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :control])} "Control Ring"]]
   [:p [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :scrime])} "Scrime"]]
   [:p [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :manage])} "Manage Class"]]
   [:p [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :message])} "PA Message"]]
   [:p [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :ring])} "View Ring Plan"]]
   [:p [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :times])} "Enter Course Times"]]
   ])

(defn start-panel []
  [:div
   [:h1 "Ring Party Tablet - Ring 1"]
   [:p [:button {:on-click (fn [] (re-frame/dispatch [::events/populate-classes])
                             (re-frame/dispatch [::events/populate-users]))} "Startup"]]
   [:p [:button {:on-click #(re-frame/dispatch [::events/set-active-panel :selector])} "Use the System"]]])

(defn main-panel []
  (let [active        (re-frame/subscribe [::subs/active-panel])
        class-details (re-frame/subscribe [::subs/active-class-string])]
    (fn []
      [:div
       [:div.title @class-details]
       (condp = @active
         :start [start-panel]
         :selector [selector]
         :control [control-panel]
         :scrime [scrime-panel]
         :manage [manage-class-panel]
         :message [info-panel]
         :ring [info-panel]
         :times [info-panel]
         :options [options-panel])])))
