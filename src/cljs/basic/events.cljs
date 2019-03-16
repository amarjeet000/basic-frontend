(ns basic.events
  (:require
   [re-frame.core :as re-frame]
   [basic.db :as db]))


(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))


(defn increase-count []
  (swap! db/default-db assoc-in [:name] (inc (:name @db/default-db))))
