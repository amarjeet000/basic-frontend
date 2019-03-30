(ns basic.views
  (:require
    [basic.queries :as q]
    [basic.events :as events]
    [soda-ash.core :as sa]
    [markdown-to-hiccup.core :as m]))


;; Counter example
;; -------------------------------------------------------------

(defn counter-view []
  [:div
   [:h1 "Hello again from " (q/counter)]
   [sa/Button {:content "Click me"
               :onClick #(events/increase-count)}]])



;; Blog App
;; -------------------------------------------------------------

;; Logo

(defn logo []
  [:div
   [sa/Image {:src "img/logo.png"
              :size "small"
              :centered true
              :circular true}]])

;; Menu

(defn menu []
  (let [page (q/current-page)]
    [:div
     [sa/Menu {:stackable true
               :inverted true
               :color "blue"}
      [sa/MenuItem {:content "Home"
                    :icon "home"
                    :active (= :home page)
                    :onClick #(events/visit-page :home)}]
      [sa/MenuItem {:content "About"
                    :color "teal"
                    :active (= :about page)
                    :onClick #(events/visit-page :about)}]]]))


;; Home page

(defn article-card [title content pub-date topics]
  [sa/Card {:header title
            :meta pub-date
            :extra topics
            :fluid true
            :onClick #(events/read-article {:title title
                                            :content content
                                            :date pub-date
                                            :topics topics})}])


(defn home []
  (let [arts (q/articles)]
    [:div
     [sa/CardGroup
      (for [art arts]
        ^{:key (:title art)}
         [article-card (:title art) (:content art) (:date art) (:topics art)])]]))



;; Article page

(defn render-md [md-content]
  (-> md-content
      (m/md->hiccup)
      (m/component)))


(defn article-page []
  (let [art (q/current-article)]
    [:div
     (render-md (:content art))]))



;; About page

(defn about []
  (let [a (q/about)]
    [:div
     (render-md a)]))


;; Form example

(defn subscription-form []
  (let [email (q/temp-email)]
    [:div
     [sa/Form
      [sa/FormInput {:label "Email"
                     :type "email"
                     :required true
                     :onChange #(events/temp-email (-> % .-target .-value))}]
      [sa/FormButton {:content "SUBSCRIBE"
                      :color "teal"
                      :onClick #(events/save-email email)}]]]))


;; Grid example

(defn home-grid []
  [:div
   [sa/Grid {:stackable true}
    [sa/GridRow
     [sa/GridColumn {:width "11"}
      [home]]
     [sa/GridColumn {:width "5"}
      [:div#side-bar
       [subscription-form]]
      [:br]]]]])




;; Logical navigation

(def view-panel-map
  {:home [home-grid]
   :about [about]
   :article-page [article-page]})


;; Main view
(defn main-panel []
  (let [v (q/current-page)]
    [:div
     [logo]
     [:br]
     [menu]
     [:br]
     [view-panel-map v]]))
