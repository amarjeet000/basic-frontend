(ns basic.queries
  (:require
    [basic.db :as db]))

;; Counter example
;; -------------------------------------------------------------

(defn counter []
  (:name @db/total-count))


;; Blog App
;; -------------------------------------------------------------

(defn articles []
  (:articles @db/blog-db))

(defn current-article []
  (:current-article @db/blog-db))

(defn current-page []
  (:current-page @db/blog-db))

(defn about []
  (:about @db/blog-db))

(defn temp-email []
  ())
