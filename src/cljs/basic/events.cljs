(ns basic.events
  (:require
   [re-frame.core :as re-frame]
   [basic.db :as db]))


;; Counter example
;; -------------------------------------------------------------

(defn increase-count []
  (swap! db/total-count assoc-in [:name] (inc (:name @db/total-count))))


;; Blog App
;; -------------------------------------------------------------

(defn read-article [article]
  (swap! db/blog-db assoc :current-article article :current-page :article-page))

(defn visit-page [page]
  (swap! db/blog-db assoc :current-page page))

(defn temp-email [email]
  ())

(defn save-email [email]
  ())
