(ns basic.db
  (:require [reagent.core :as r]))


(def default-db
  (r/atom {:name 0}))
