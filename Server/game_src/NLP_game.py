import json

from eunjeon import Mecab
import random

class NLP1:
    def sentence_extraction(sentences, m):
        final_num = [] #문장 내 모든 마침표들의 위치를 구함
        morp = m.morphs(sentences) # 문장을 품사로 구분

        for i in range(len(morp)) :
            if morp[i] == '.':
                final_num.append(i)
            elif morp[i] == '?':
                final_num.append(i)
            elif morp[i] == '!':
                final_num.append(i)

        all_input_sentences = [] #마침표를 기준으로 입력받은 문장을 나눔
        for j in range(len(final_num)):
            if j == 0 :
                all_input_sentences.append(morp[:final_num[j]+1])
            elif j > 0 :
                all_input_sentences.append(morp[final_num[j-1] + 1 : final_num[j] + 1])

        #입력받은 문장들을 마침표를 기점으로 나눔

        dot_input_sentences = [] #온점만 있는 문장들을 모아놓는 리스트
        for l in range(len(all_input_sentences)) :
            if '.' or '!' in all_input_sentences[l] :#all_input_sentences에서 온점과 느낌표로 끝나는 문장만 dot_input_sentences에 저장한다.
                dot_input_sentences.append(all_input_sentences[l])
        #입력받은 문장들 중에서 평서문만 추출

        return dot_input_sentences

    def make_important_senteces(sentences):
        m = Mecab()

        random_dot_input_sentences = random.sample(NLP1.sentence_extraction(sentences, m), 1)
        important_sentences = []#평서문 들 중 NNG, NNP, NP와 같은 품사를 가지는 단어들을 추출한 리스트
        Str_dot_sentences = []#리스트화 되어있는 문자열들을 띄어쓰기 없이 하나의 문자열로 변환
        for letter in range(len(random_dot_input_sentences)):#추출한 평서문의 개수만큼 roop
            Str_dot_sentences = ''.join(random_dot_input_sentences[letter])

            for num in range(len(random_dot_input_sentences[letter])):#추출한 평서문 내 첫번째 리스트에 있는 문장의 길이만큼 roop
                if 'NNG' in m.pos(Str_dot_sentences)[num][1] :
                    important_sentences.append(random_dot_input_sentences[letter][num])
                elif 'NNP' in m.pos(Str_dot_sentences)[num][1] :
                    important_sentences.append(random_dot_input_sentences[letter][num])
                elif 'NP' in m.pos(Str_dot_sentences)[num][1]:
                    important_sentences.append(random_dot_input_sentences[letter][num])

        remove_list = ['어제', '오늘', '내일', '그저께', '모레', '나', '어저께', '은']
        for remove_word in remove_list:
            if remove_word in important_sentences :
                important_sentences.remove(remove_word)
        return important_sentences, random_dot_input_sentences
    
    def make_qa(self, senteces):
        important_sentences, random_dot_input_sentences = NLP1.make_important_senteces(senteces)
        index_important_sentences = []
        answer_sentences = []#빈칸에 들어갈 핵심용어가 저장되어 있는 list
        #NP, NNP, NNG 품사들 중 랜덤으로 선택된 핵심용어의 index가 저장되는 list

        index_important_sentences.append(random_dot_input_sentences[0].index(random.sample(important_sentences, 1)[0]))

        answer_sentences = random_dot_input_sentences[0][index_important_sentences[0]]
        random_dot_input_sentences[0][index_important_sentences[0]] = '_______'
        #퀴즈를 만들기 위하여 랜덤으로 추출된 온점으로 끝나는 문장 내에서 랜덤으로 핵심용어를 추출,
        #추출된 핵심용어를 빈칸으로 대체

        question = ' '.join(random_dot_input_sentences[0])
        answer = answer_sentences
        return json.dumps({'question': question, 'answer': answer}, ensure_ascii=False)
