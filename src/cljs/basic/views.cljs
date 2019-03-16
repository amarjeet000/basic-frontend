(ns basic.views
  (:require
    [re-frame.core :as re-frame]
    [basic.subs :as subs]
    [basic.events :as events]
    [soda-ash.core :as sa]))


(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 "Hello again from " (subs/counter)]
     [sa/Button {:content "Click me"
                 :onClick #(events/increase-count)}]]))

