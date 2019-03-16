(ns basic.subs
  (:require
    [re-frame.core :as re-frame]
    [basic.db :as db]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(defn counter []
  (:name @db/default-db))
